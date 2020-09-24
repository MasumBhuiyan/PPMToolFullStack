import React, { Component } from 'react';
import { findProject, createProject } from '../../actions/projectActions';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import classnames from 'classnames';

class UpdateProject extends Component {
    constructor() {
        super()
        this.state = {
            id: "",
            projectName: "",
            projectIdentifier: "",
            projectDescription: "",
            startDate: "",
            endDate: "",
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.errors) {
            this.setState({
                errors: nextProps.errors
            })
        }
        const {
            id,
            projectName,
            projectIdentifier,
            projectDescription,
            startDate,
            endDate
        } = nextProps.project

        this.setState({
            id,
            projectName,
            projectIdentifier,
            projectDescription,
            startDate,
            endDate
        });
    }
    componentDidMount() {
        const { projectIdentifier } = this.props.match.params;
        this.props.findProject(projectIdentifier, this.props.history);
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    onSubmit(event) {
        event.preventDefault()
        const updatedProject = {
            id: this.state.id,
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            projectDescription: this.state.projectDescription,
            startDate: this.state.startDate,
            endDate: this.state.endDate
        }
        this.props.createProject(updatedProject, this.props.history);
    }

    render() { 
        const { errors } = this.state;
        return (  
            <div className="project">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Update Project form</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        className={classnames(
                                            "form-control form-control-lg", {
                                            "is-invalid": errors.projectName 
                                        })} 
                                        placeholder="Project Name" 
                                        name="projectName"
                                        value={this.state.projectName}
                                        onChange = {this.onChange}
                                    />
                                    {errors.projectName && (
                                        <div className="invalid-feedback"> { errors.projectName } </div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        className="form-control form-control-lg" 
                                        placeholder="Unique Project ID"
                                        name="projectIdentifier"
                                        value={this.state.projectIdentifier}
                                        disabled 
                                    />
                                </div>
                                <div className="form-group">
                                    <textarea 
                                        className={classnames(
                                            "form-control form-control-lg", {
                                            "is-invalid": errors.projectDescription
                                        })} 
                                        placeholder="Project Description"
                                        name="projectDescription"
                                        value={this.state.projectDescription}
                                        onChange = {this.onChange}>    
                                    </textarea>
                                    {errors.projectDescription && (
                                        <div className="invalid-feedback"> { errors.projectDescription } </div>
                                    )}
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        className="form-control form-control-lg" 
                                        name="startDate"
                                        value={this.state.startDate} 
                                        onChange = {this.onChange}
                                    />
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        className="form-control form-control-lg" 
                                        name="endDate"
                                        value={this.state.endDate}   
                                        onChange = {this.onChange} 
                                    />
                                </div>
                                <input 
                                    type="submit" 
                                    className="btn btn-primary btn-block mt-4" 
                                />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
 
UpdateProject.propTypes = {
    findProject: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    project: state.project.project,
    errors: state.errors
})
export default connect(
    mapStateToProps,
    { findProject, createProject }
) (UpdateProject);